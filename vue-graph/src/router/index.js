import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '@/components/HomePage'
import Login from '@/components/login/Login'
import UserPage from '@/components/UserPage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/homePage',
      name: 'homePage',
      component: HomePage
    },
    {
      path: '/userPage',
      name: 'userPage',
      component: UserPage
    }
  ]

})
