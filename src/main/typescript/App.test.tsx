import {Main} from "./components/MainComponent";
import "@testing-library/jest-dom";
import {render, screen} from "@testing-library/react";
import React, {ComponentType, ReactElement} from "react";
import {configureStore, Store} from "@reduxjs/toolkit";
import {Provider} from "react-redux";
import rootReducer from "./reducers";

describe("App", function () {
  const makeStore = (): Store => {
    return configureStore({
      reducer: rootReducer,
    });
  };

  const wrapComponent = (
      Component: ComponentType,
      store: Store | null = null,
      props = {}
  ): ReactElement => {
    return (
        <Provider store={store || makeStore()}>
          <Component {...props} />
        </Provider>
    );
  };

  test("タイトルが表示されているか", () => {
    const {getByText} = render(wrapComponent(Main));
    screen.debug();
    expect(getByText("Hello React!")).toBeInTheDocument();
  });
});
