import { useState } from "react";
import axios from "axios";
import { register } from "../auth/authAPi.tsx";

const Register = () => {
    const [email, setEmail] = useState("");
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");

    const handleRegister = async () => {
        if (!email || !name || !password) {
            setMessage("모든 필드를 입력해주세요.");
            return;
        }

        try {
            const response = await register(name, email, password);
            if (response.status === 201 || response.status === 200) {
                setMessage("회원가입 성공!");
                alert("회원가입 성공")!
                window.location.href="http://localhost:5173/login"
            }
        } catch (err: any) {
            console.log("에러 발생:", err);
            setMessage(err.response?.data?.message || "회원가입 실패");
        }
    };

    return (
        <section
            className="vh-100 bg-image"
            style={{
                backgroundImage:
                    "url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp')",
            }}
        >
            <div className="mask d-flex align-items-center h-100 gradient-custom-3">
                <div className="container h-100">
                    <div className="row d-flex justify-content-center align-items-center h-100">
                        <div className="col-12 col-md-9 col-lg-7 col-xl-6">
                            <div className="card" style={{ borderRadius: "15px" }}>
                                <div className="card-body p-5">
                                    <h2 className="text-uppercase text-center mb-5">
                                        회원가입
                                    </h2>

                                    <form>
                                        <div className="form-outline mb-4">
                                            <input
                                                type="text"
                                                id="form3Example1cg"
                                                className="form-control form-control-lg"
                                                placeholder=""
                                                value={name}
                                                onChange={(e) => setName(e.target.value)}
                                            />
                                            <label
                                                className="form-label"
                                                htmlFor="form3Example1cg"
                                            >
                                                이름
                                            </label>
                                        </div>

                                        <div className="form-outline mb-4">
                                            <input
                                                type="email"
                                                id="form3Example3cg"
                                                className="form-control form-control-lg"
                                                placeholder=""
                                                value={email}
                                                onChange={(e) => setEmail(e.target.value)}
                                            />
                                            <label
                                                className="form-label"
                                                htmlFor="form3Example3cg"
                                            >
                                                이메일
                                            </label>
                                        </div>

                                        <div className="form-outline mb-4">
                                            <input
                                                type="password"
                                                id="form3Example4cg"
                                                className="form-control form-control-lg"
                                                placeholder=""
                                                value={password}
                                                onChange={(e) => setPassword(e.target.value)}
                                            />
                                            <label
                                                className="form-label"
                                                htmlFor="form3Example4cg"
                                            >
                                                비밀번호
                                            </label>
                                        </div>

                                        <div className="d-flex justify-content-center">
                                            <button
                                                type="button"
                                                onClick={handleRegister}
                                                className="btn btn-success btn-block btn-lg gradient-custom-4 text-body"
                                            >
                                                가입하기
                                            </button>
                                        </div>

                                        <p className="text-center text-muted mt-5 mb-0">
                                            이미 계정이 있으신가요?{" "}
                                            <a href="/login" className="fw-bold text-body">
                                                <u>로그인하기</u>
                                            </a>
                                        </p>
                                    </form>

                                    {message && (
                                        <p className="text-center mt-3" style={{ color: "red" }}>
                                            {message}
                                        </p>
                                    )}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default Register;
