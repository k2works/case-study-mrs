import {render, screen} from "@testing-library/react";
import "@testing-library/jest-dom/extend-expect";
import App from "./App.jsx";

describe("App", function () {
  test("タイトルが表示されているか", () => {
    render(<App/>);
    expect(screen.getByText("Hello React!")).toBeInTheDocument();
  });
});
