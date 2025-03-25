import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { getCookie, parseJwt } from "../utils/cookie.ts";

const NewBlog = () => {
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    const [email, setEmail] = useState("");
    const navigate = useNavigate();

    const token = getCookie("jwt");
    const parsed = token ? parseJwt(token) : null;

    // 🔥 email을 처음부터 상태에 세팅!
    useEffect(() => {

        if (parsed && parsed.sub) {
            setEmail(parsed.sub);
        } else {
            alert("로그인 후 작성이 가능합니다.");
            navigate("/login");
        }
    }, []);

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();

        axios.post("http://localhost:8091/api/boards", { title, content },
            {
                headers: {
                    Authorization: `Bearer ${token}`
                },
                withCredentials:true
            }
            )
            .then(() => {
                alert("게시글이 등록되었습니다.");
                navigate("/");
            })
            .catch(error => {
                console.error("게시글 등록 실패", error);
            });
    };

    return (
        <div className="container mt-5">
            <h2 className="mb-4">✏️ 새 게시글 작성</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label className="form-label">작성자</label>
                    <input
                        type="text"
                        className="form-control fw-bold"
                        value={email}
                        readOnly
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">제목</label>
                    <input
                        type="text"
                        className="form-control"
                        value={title}
                        onChange={e => setTitle(e.target.value)}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">내용</label>
                    <textarea
                        className="form-control"
                        rows={5}
                        value={content}
                        onChange={e => setContent(e.target.value)}
                        required
                    ></textarea>
                </div>
                <button type="submit" className="btn btn-primary">등록</button>
            </form>
        </div>
    );
};

export default NewBlog;
