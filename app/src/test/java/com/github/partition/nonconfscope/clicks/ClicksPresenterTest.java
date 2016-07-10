package com.github.partition.nonconfscope.clicks;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClicksPresenterTest {

  @Mock
  ClicksView viewMock;
  ClicksPresenter sut;

  @Before
  public void setUp() throws Exception {
    sut = new ClicksPresenter();
  }

  @Test
  public void delivers_clicks_to_view() throws Exception {
    sut.setView(viewMock);

    sut.click();
    sut.click();

    verify(viewMock).setCount(1);
    verify(viewMock).setCount(2);
  }

  @Test
  public void delivers_0_on_start() throws Exception {
    sut.setView(viewMock);
    verify(viewMock).setCount(0);
  }

  @Test
  public void survives_clicks_without_view() throws Exception {
    sut.click();
  }

  @Test
  public void delivers_clicks_to_new_views() throws Exception {
    sut.click();
    sut.click();

    sut.setView(viewMock);

    verify(viewMock).setCount(2);
  }
}
