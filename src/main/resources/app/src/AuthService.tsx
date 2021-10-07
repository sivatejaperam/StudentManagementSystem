import axios from "axios"


class AuthService {

    setUpAxiosInterceptors = () => {
        axios.interceptors.request.use((request: any) => {
            if (!(request.url.indexOf('/auth/login') > 0)) {
                request.headers['Authorization'] = "Bearer "+ this.getAccessToken();
            }
            return request;
        });
        axios.interceptors.response.use((response: any) => {
            if (response.config.url.indexOf('/auth/login') > 0) {
                if (response.data && response.data.access_token && response.data.refresh_token) {
                    this.storeAccessTokens(response.data.access_token, response.data.refresh_token)
                }
            }
            return response;
        });
    }

    storeAccessTokens(access_token: string, refresh_token: string) {
        localStorage.setItem("access_token", access_token);
        localStorage.setItem("refresh_token", refresh_token);
    }

    getAccessToken() {
        return localStorage.getItem("access_token")
    }

    getRefreshToken() {
        return localStorage.getItem("refresh_token");
    }
}

export default new AuthService();