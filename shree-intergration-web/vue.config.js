const path = require("path");
function resolve(dir) {
    return path.resolve(__dirname, dir);
}
module.exports = {
    chainWebpack: (config) => {
        config.resolve.alias
            .set('vue$', 'vue/dist/vue.esm.js')
            .set('@', resolve('src'))
            .set('assets', resolve('src/assets'))
            .set('components', resolve('src/components'))
            .set('utils', resolve('src/utils'))
            .set('store', resolve('src/store'))
            .set('router', resolve('src/router'));

        config.resolve.extensions
            .add('.js')
            .add('.vue')
            .add('.styl');

        // config.module.rule('svg').uses.clear();
        // config.module
        // .rule('svg')
        // .use('raw-loader')
        // .loader('raw-loader');
    },
    runtimeCompiler: true,
    devServer: {
        proxy: {
            '/api': {
                target: 'http://api.shree.top',
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}
