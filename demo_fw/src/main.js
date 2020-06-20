import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'


//aixos
import './axios.js'
import axios from 'axios'

//element ui
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"

//mavon-editor
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
Vue.use(mavonEditor)



Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.use(Element)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
