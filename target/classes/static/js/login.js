new Vue ({
    el: "#login",
    data: {
        labelPosition: "left",
        visible: false,
        loginForm: {
            name: "",
            password: "",
        },
        regisForm: {
            name: "",
            password: "",
            checkpassword: ""
        },
    },
    methods:{
        handleLogin(){
            let this_ = this;
            if (this.loginForm.name === '' && this.loginForm.password === ''){
                this.$message.error('啥都不输入你想添加啥？');
            }
            else {
                axios.get('/SignIn',{
                    params: {
                        name: this.loginForm.name,
                        password: this.loginForm.password,
                    }
                })
                    .then(function (response) {
                        console.log(response)
                        if (response.data === 1){
                            // 这里试了 data[0] == '1', data === "1", 各种组合，可是没想到后端传来的JSON直接转换成了数字...
                            window.location.href="/main";
                        }
                        else {
                            this_.$message.error('账号或密码错误!');
                        }

                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }


        },
        handleRegister(){

        }
    }
})