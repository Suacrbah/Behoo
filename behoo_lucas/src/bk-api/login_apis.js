import axios from 'axios'

const AXIOS = axios.create({
  baseURL: 'http://localhost:8888/api',
  timeout: 1000
});


export default {
  auth(user, pwd) {
    return AXIOS.post(`/login/` + user + '/' + pwd);
  },
}
