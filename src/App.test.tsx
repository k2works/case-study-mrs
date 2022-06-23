import {MainComponent as Main} from "./component/main-component";
import "@testing-library/jest-dom";
import {render, screen} from "@testing-library/react";
import React from "react";

describe("App", function () {
  test("タイトルが表示されているか", () => {
    const {getByText} = render(<Main/>);
    screen.debug();
    expect(getByText("Hello React!")).toBeInTheDocument();
  });
});
