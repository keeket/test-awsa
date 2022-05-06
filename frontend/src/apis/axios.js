import axios from "axios";

const request = axios.create({
  baseURL: "http://localhost"
  // baseUrl: "http://demomer-env.eba-srdm2jqy.us-east-1.elasticbeanstalk.com"
});

export default request;
