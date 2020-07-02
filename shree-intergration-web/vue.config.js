const path = require("path");

module.exports = {
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
