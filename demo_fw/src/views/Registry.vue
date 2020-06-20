<template>

    <div>
        <el-container>
            <el-header>
                <img class="blogo" src="../assets/logo.png" alt="logo loading...">
            </el-header>
            <el-main>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="用户名" prop="username">
                        <el-input v-model="ruleForm.username"></el-input>
                    </el-form-item>

                    <el-form-item label="密码" prop="hashedPassword">
                        <el-input type="password" v-model="ruleForm.hashedPassword"></el-input>
                    </el-form-item>

                    <el-form-item  label="邮箱" prop="email" >
                        <el-col :span="13">
                            <el-input  type="email" v-model="ruleForm.email" ref="emailForm"></el-input>
                        </el-col>
                        <el-col :span="11">
                            <el-button type="primary" @click="sendEmail('emailForm')">发送验证码</el-button>
                        </el-col>
                    </el-form-item>

                    <el-form-item label="验证码" prop="validCode">
                        <el-input  v-model="ruleForm.validCode"></el-input>
                    </el-form-item>


                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
                        <el-button @click="resetForm('ruleForm')">清空</el-button>
                    </el-form-item>
                </el-form>

            </el-main>
        </el-container>

    </div>
</template>

<script>
    export default {
        name: "Registry",
        data() {
            var validateCode = (rule, value, callback) => {
                if(this.ruleForm.validCode===''){
                    callback(new Error('验证码不为空'));
                }else{
                    if(this.ruleForm.validCode === this.codeRight){
                        callback()
                    }else{
                        callback(new Error('验证码错误'));
                    }
                }
                //}
            };
            return {
                ruleForm: {
                    username: '',
                    hashedPassword: '',
                    email:'',
                    validCode:''
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
                    ],
                    hashedPassword: [
                        { required: true, message: '请选择密码', trigger: 'change' }
                    ],
                    email: [
                        { required: true, message: '邮箱不能为空', trigger: 'change' }
                    ],
                    validCode:[
                        { validator : validateCode, trigger: 'change' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        const _this = this  //vue 的 this
                        this.$axios.post('/registry', this.ruleForm).then(res => {
                            _this.$router.push("/login")
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields()
            },
            sendEmail(formName) {
                const _this = this
                this.sendCodeUrl='/registryValid/'+this.$refs[formName].value
                console.log(this.sendCodeUrl)
                this.$axios.get('http://localhost:8889/'+this.sendCodeUrl).then(
                    res => {
                        _this.codeRight=res.data['msg']
                        //console.log('the turecode is'+_this.codeRight)
                    })


            }
        }
    }
</script>
<style scoped>
    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        /*background-color: #E9EEF3;*/
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }

    .blogo {
        height: 70%;
        margin-top: 10px;
    }

    .demo-ruleForm {
        max-width: 500px;
        margin: 0 auto;
    }

</style>