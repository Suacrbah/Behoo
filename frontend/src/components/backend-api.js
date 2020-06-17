import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `/api`,
  timeout: 1000
});

export default {
    auth(user, password) {
        return AXIOS.post(`/login/` + user + '/' + password);
    },
}
