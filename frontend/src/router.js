import Vue from 'vue'
import Router from 'vue-router'
import Editor from '@/components/Editor'
import LoginForm from '@/components/LoginForm.vue'

Vue.use(Router);

const router = new Router({
    mode: 'history', // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
    routes: [
        { path: '/editor', component: Editor },
        { path: '/login', component: LoginForm },
        { path: '/', component: LoginForm },
    ]
})

export default router;
