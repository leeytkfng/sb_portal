import { useState } from "react";
import { login } from "../auth/authAPi.tsx";
import "bootstrap/dist/css/bootstrap.min.css"; // ✅ Bootstrap 추가
import "bootstrap-icons/font/bootstrap-icons.min.css"; // ✅ Bootstrap Icons 추가

const Login = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");

    const handleLogin = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await login(email, password);
            console.log("✅ 로그인 성공", response.data);

            localStorage.setItem("token" , response.data.token);

            document.cookie = `jwt=${response.data.token}; Path=/;`;

            alert("로그인 성공!");
            setMessage("로그인 성공!");

            window.location.href = `http://localhost:8087?token=${response.data.token}`;
        } catch (err: any) {
            console.log("❌ 로그인 실패:", err);
            setMessage("로그인 실패. 다시 시도하세요.");
        }
    };

    return (
        <section className="vh-100 d-flex align-items-center justify-content-center" style={{ backgroundColor: "#eee" }}>
            <div className="container py-5 h-100">
                <div className="row d-flex align-items-center justify-content-center h-100">
                    <div className="col-md-8 col-lg-7 col-xl-6">
                        <img
                            src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
                            className="img-fluid"
                            alt="Login image"
                        />
                    </div>
                    <div className="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
                        <form onSubmit={handleLogin}>
                            <div className="form-outline mb-4">
                                <input
                                    type="email"
                                    id="form1Example13"
                                    className="form-control form-control-lg"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    required
                                />
                                <label className="form-label" htmlFor="form1Example13">
                                    이메일 주소
                                </label>
                            </div>

                            <div className="form-outline mb-4">
                                <input
                                    type="password"
                                    id="form1Example23"
                                    className="form-control form-control-lg"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required
                                />
                                <label className="form-label" htmlFor="form1Example23">
                                    비밀번호
                                </label>
                            </div>

                            <div className="d-flex justify-content-between align-items-center mb-4">
                                <div className="form-check">
                                    <input
                                        className="form-check-input"
                                        type="checkbox"
                                        id="rememberMe"
                                    />
                                    <label className="form-check-label" htmlFor="rememberMe">
                                        로그인 유지
                                    </label>
                                </div>
                                <a href="#">비밀번호 찾기</a>
                            </div>

                            <button
                                type="submit"
                                className="btn btn-primary btn-lg btn-block w-100"
                            >
                                로그인
                            </button>

                            <div className="text-center mt-3">
                                <p>
                                    계정이 없으신가요? <a href="/">회원가입</a>
                                </p>
                            </div>
                        </form>
                        {message && <p className="text-center mt-3">{message}</p>}
                    </div>
                </div>
            </div>
        </section>
    );
};

export default Login;
