import Vue from 'vue'
import VueRouter from 'vue-router'

import Login from '../views/Login.vue'
import Registry from '../views/Registry.vue'
import Questions from '../views/Questions.vue'
import QuestionEdit from '../views/QuestionEdit.vue'
import QuestionDetail from '../views/QuestionDetail.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Index',
    redirect: {name: "Questions"}
  },
  {
    path: '/questions',
    name: 'Questions',
    component: Questions
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/registry',
    name: 'Registry',
    component: Registry
  },
  {
    path: '/question/add',
    name: 'QuestionEdit',
    component: QuestionEdit
  },
  {
    path: '/question/:questionId',
    name: 'QuestionDetail',
    component: QuestionDetail
  },
  {
    path: '/question/:questionId/edit/',
    name: 'QuestionEdit',
    component: QuestionEdit
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
