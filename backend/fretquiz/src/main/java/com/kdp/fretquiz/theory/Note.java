package com.kdp.fretquiz.theory;

import com.kdp.fretquiz.Util;

import java.util.Collection;
import java.util.regex.Pattern;

public record Note(WhiteKey whiteKey,
                   Accidental accidental,
                   Octave octave)
{
    /**
     * Matches a note name like "C4", "Gbb6", or "E#2".
     */
    public static final Pattern regexPattern = Pattern.compile("([A-Z])(#{1,2}|b{1,2})?(\\d)");

    /**
     * Parses a note name like "Cb4" into a note record, like Note[whiteKey=C, accidental=FLAT, octave=FOUR]
     *
     * @param name a string consisting of a capital letter A-G, an optional accidental ('b' or '#'), and an octave number
     * @return a Note
     */
    public static Note from(String name)
    {
        final var matcher = regexPattern.matcher(name);

        if (! matcher.matches()) {
            throw new IllegalArgumentException();
        }

        // for the note 'E##3': matcher.group(1) == 'E', matcher.group(2) == '##', matcher.group(3) == '3'
        final var whiteKey = WhiteKey.valueOf(matcher.group(1));

        // find the accidental, if it exists
        final var match2 = matcher.group(2);

        // if there was no accidental, set it to be an empty string instead of null so it can be parsed correctly
        final var accidental = Accidental.from(match2 == null ? "" : match2);

        final var octave = Octave.from(matcher.group(3));

        return new Note(whiteKey, accidental, octave);
    }

    /**
     * Returns a random note.
     */
    public static Note random()
    {
        final var whiteKey = Util.randomElement(WhiteKey.values());
        final var accidental = Util.randomElement(Accidental.values());
        final var octave = Util.randomElement(Octave.values());

        return new Note(whiteKey, accidental, octave);
    }

    public static Note random(Collection<Accidental> accidentals)
    {
        final var whiteKey = Util.randomElement(WhiteKey.values());
        final var accidental = (Accidental) Util.randomElement(accidentals.toArray());
        final var octave = Util.randomElement(Octave.values());

        return new Note(whiteKey, accidental, octave);
    }

    public static Note randomBetween(Note low, Note high, Collection<Accidental> accidentals)
    {
        final var lowMidi = low.midiNum();
        final var highMidi = high.midiNum();

        if (lowMidi >= highMidi) {
            throw new IllegalArgumentException("first argument must be a lower note than the second");
        }

        Note note;
        int midi;

        do {
            note = Note.random(accidentals);
            midi = note.midiNum();
        } while (midi < lowMidi || midi > highMidi);

        return note;
    }

    public String name()
    {
        return whiteKey.toString() + accidental.val + octave.val;
    }

    /**
     * A number representing the pitch (without the octave) as the number of half steps from 'C'.
     * e.g. C -> 0, C# -> 1, etc.
     */
    public int pitchClass()
    {
        return whiteKey.halfStepsFromC() + accidental.halfStepOffset();
    }

    /**
     * The note's midi number. C4 is 60, C#4 is 61, etc.
     */
    public int midiNum()
    {
        return pitchClass() + (12 * (octave.val + 1));
    }

    /**
     * Returns true if `this` is enharmonic with the given note.
     * E##4, F#4, & Gb4 would all be considered enharmonic.
     */
    public boolean isEnharmonicWith(Note note)
    {
        return midiNum() == note.midiNum();
    }

    /**
     * Returns the note a half step higher.
     */
    public Note next()
    {
        // If we're at pitchClass == 11 (the notes "B", "A##", "Cb"), increment the octave.
        // Otherwise, the octave stays the same.
        final var oct = pitchClass() == 11 ? octave.next() : octave;

        var key = whiteKey;
        var acc = accidental;

        if (accidental == Accidental.NONE) {
            if (whiteKey == WhiteKey.B || whiteKey == WhiteKey.E) {
                key = whiteKey.next();
            } else {
                acc = Accidental.SHARP;
            }
        } else if (accidental == Accidental.SHARP) {
            key = whiteKey.next();
            acc = (whiteKey == WhiteKey.B || whiteKey == WhiteKey.E)
                    ? Accidental.SHARP
                    : Accidental.NONE;
        } else if (accidental == Accidental.FLAT) {
            acc = Accidental.NONE;
        }

        return new Note(key, acc, oct);
    }

    /**
     * Returns the note that is the given number of half-steps higher.
     *
     * @param halfSteps must be a positive number
     */
    public Note transpose(int halfSteps)
    {
        if (halfSteps < 0) {
            throw new IllegalArgumentException("halfSteps must be a positive number");
        }

        var note = new Note(whiteKey, accidental, octave);

        while (halfSteps > 0) {
            note = note.next();
            halfSteps--;
        }

        return note;
    }
}
