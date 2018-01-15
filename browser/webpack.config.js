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
        extensions: ['.ts', '.js', '.vue'],
        alias: {
            assets: path.join(__dirname, 'assets/'),
            'vue$': 'vue/dist/vue.esm.js'
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
            "Config.HOSTNAME": JSON.stringify("zespolowe.ddns.net"),
            "Config.PORT": 8448
        })
    ],
    devServer: {
        contentBase: path.join(__dirname, 'dist'),
        port: 8090
    },
    module: {
        rules: [
            {
                test: /\.vue$/,
                loader: 'vue-loader',
                options: {
                    loaders: {
                        'sass': 'vue-style-loader!css-loader!sass-loader'
                    },
                    esModule: true
                }
            },
            {
                test: /\.ts$/, enforce: "pre", loader: 'tslint-loader',
                options: {
                    appendTsSuffixTo: [/\.vue$/]
                }
            },
            {
                test: /assets[\/\\]/,
                loader: 'file-loader?name=assets/[hash].[ext]'
            },
            {
                test: /mdbootstrap[\/\\]/,
                loader: 'file-loader?name=mdb/[hash].[ext]'
            },
            {
                test: /\.ts$/,
                loader: 'ts-loader',
                exclude: '/node_modules/',
                options: {
                    appendTsSuffixTo: [/\.vue$/]
                }
            }
        ]
    },
    devtool: 'source-map'
};

