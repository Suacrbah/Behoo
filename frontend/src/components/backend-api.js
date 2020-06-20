import axios from 'axios'

// axios is a promise-based HTTP client
const AXIOS = axios.create({
  baseURL: 'http://localhost:8888/api',
  timeout: 1000
});

// Expose functions defined using axios
export default {
    auth(user, pwd) {

        var response= AXIOS.post(`/login/` + user + '/' + pwd);

        console.log(response)
        return response
    },
    hello() {
        return AXIOS.get(`/app1`);
    },
}
