const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");

const env = process.env.NODE_ENV || "development";
const isDevelopment = env === "development";

module.exports = {
    mode: env,
    target: ["web", "es5"],
    devtool: isDevelopment ? "source-map" : false,
    entry: "./src/main/typescript/index.tsx",
    output: {
        filename: "main.js",
        path: path.resolve(__dirname, "public"),
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: "./index.html",
            filename: "index.html",
        }),
    ],
    resolve: {
        extensions: [".ts", ".tsx", ".js", ".jsx", ".json"],
    },
    devServer: {
        historyApiFallback: true,
    },
    module: {
        rules: [
            {
                test: /\.(ts|tsx)$/,
                use: "ts-loader",
            },
            {
                test: /\.(js|jsx)$/,
                loader: "babel-loader",
                exclude: /node_modules/,
                options: {presets: ["@babel/env", "@babel/preset-react"]},
            },
            {
                test: /\.(scss|css)/,
                use: [
                    "style-loader",
                    {
                        loader: "css-loader",
                        options: {
                            url: true,
                            sourceMap: isDevelopment,

                            // 0 => no loaders (default);
                            // 1 => postcss-loader;
                            // 2 => postcss-loader, sass-loader
                            importLoaders: 2,
                        },
                    },
                    {
                        loader: "sass-loader",
                        options: {
                            sourceMap: isDevelopment,
                        },
                    },
                ],
            },
            {
                test: /\.(gif|png|jpg|svg)$/,
                type: "asset/inline",
            },
        ],
    },
};
