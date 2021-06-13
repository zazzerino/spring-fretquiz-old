import Vex from 'vexflow';

export function makeVexObjects(elem: HTMLElement, width: number, height: number) {
  const renderer = new Vex.Flow.Renderer(elem, Vex.Flow.Renderer.Backends.SVG);
  renderer.resize(width, height);

  const context = renderer.getContext();

  const stave = new Vex.Flow.Stave(0, 0, width - 1);
  stave.addClef('treble');
  stave.setContext(context);

  return { renderer, context, stave };
}

export function drawNote(context: Vex.IRenderContext, stave: Vex.Flow.Stave, note: string) {

}
