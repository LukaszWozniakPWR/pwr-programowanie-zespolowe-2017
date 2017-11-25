const webpack = require('webpack');
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    entry: {
        app: [path.join(__dirname, 'src/app.ts')]
    },
    output: {
        path: path.join(__dirname, 'dist'),
        filename: 'bundle.js'
    },
    resolve: {
        extensions: ['.ts', '.js'],
        alias: {
            pixi: path.join(__dirname, 'node_modules/pixi.js/dist/pixi.js'),
            assets: path.join(__dirname, 'assets/')
        }
    },
    node: {
        fs: "empty"
    },
    plugins: [
        new HtmlWebpackPlugin({
            title: "cardgame-browser",
            template: path.join(__dirname, 'src/index.html')
        }),
        new webpack.DefinePlugin({
            "DEBUG": true,
            "Config.HOSTNAME": JSON.stringify("127.0.0.1"),
            "Config.PORT": 8448
        })
    ],
    devServer: {
        contentBase: path.join(__dirname, 'dist'),
        port: 8090
    },
    module: {
        rules: [
            { test: /\.ts$/, enforce: 'pre', loader: 'tslint-loader' },
            { test: /assets[\/\\]/, loader: 'file-loader?name=assets/[hash].[ext]' },
            { test: /\.ts$/, loader: 'ts-loader', exclude: '/node_modules/' }
        ]
    },
    devtool: 'source-map'
};

