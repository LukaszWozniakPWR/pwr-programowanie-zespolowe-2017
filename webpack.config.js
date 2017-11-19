const webpack = require('webpack');
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    entry: path.join(__dirname, 'src/app.ts'),
    output: {
        path: path.join(__dirname, 'dist'),
        filename: 'bundle.js'
    },
    resolve: {
        extensions: ['.ts', '.js'],
        alias: {
            pixi: path.join(__dirname, 'node_modules/pixi.js/dist/pixi.js')
        }
    },
    node: {
        fs: "empty"
    },
    plugins: [
        new HtmlWebpackPlugin({
            title: "cardgame-browser",
            template: path.join(__dirname, 'src/index.html')
        })
    ],
    devServer: {
        contentBase: path.join(__dirname, 'dist'),
        port: 8000
    },
    module: {
        rules: [
            { test: /\.ts$/, enforce: 'pre', loader: 'tslint-loader' },
            { test: /\.ts$/, loader: 'ts-loader', exclude: '/node_modules/' }
        ]
    },
    devtool: 'source-map'
};

