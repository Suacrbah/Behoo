import axios from 'axios'

const AXIOS = axios.create({
  baseURL: 'http://192.168.43.17:8080/api',
  timeout: 1000
});


export default {
    auth(user, pwd) {
        return AXIOS.post(`/login/` + user + '/' + pwd);
    },
}
