// vue.config.js
module.exports = {
    // proxy all webpack dev-server requests starting with /api
    // to our Spring Boot backend (localhost:8088) using http-proxy-middleware
    // see https://cli.vuejs.org/config/#devserver-proxy
    devServer: {
      proxy: {
        '/api': {
          target: 'http://192.168.43.233:8888', // this configuration needs to correspond to the Spring Boot backends' application.properties server.port
          ws: true,
          changeOrigin: true
        }
      }
    },
    // Change build paths to make them Maven compatible
    // see https://cli.vuejs.org/config/
    outputDir: 'target/dist',
    assetsDir: 'static'
  };
  