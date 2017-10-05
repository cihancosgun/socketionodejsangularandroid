import { AngularsocketiochatPage } from './app.po';

describe('angularsocketiochat App', () => {
  let page: AngularsocketiochatPage;

  beforeEach(() => {
    page = new AngularsocketiochatPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
