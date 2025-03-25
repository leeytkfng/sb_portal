import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';

const BlogDetail = () => {
    const { id } = useParams();
    const [board, setBoard] = useState<any>(null);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get(`http://localhost:8091/api/boards/${id}`)
            .then(response => {
                setBoard(response.data);
            })
            .catch(error => {
                console.error("게시글 불러오기 실패", error);
            });
    }, [id]);

    const handleDelete = () => {
        if (window.confirm("정말 삭제하시겠습니까?")) {
            axios.delete(`http://localhost:8091/api/boards/${id}`)
                .then(() => {
                    alert("삭제되었습니다.");
                    navigate("/");
                })
                .catch(error => {
                    console.error("삭제 실패", error);
                });
        }
    };

    const handleEdit = () => {
        navigate(`/board/edit/${id}`);
    };

    if (!board) return <div className="container mt-5">로딩 중...</div>;

    return (
        <div className="container mt-5">
            <h2 className="mb-4">📄 게시글 상세</h2>
            <table className="table">
                <tbody>
                <tr>
                    <th>ID</th>
                    <td>{board.id}</td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td>{board.title}</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>{board.email}</td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>{board.content}</td>
                </tr>
                </tbody>
            </table>
            <div className="d-flex gap-2">
                <button className="btn btn-warning" onClick={handleEdit}>✏️ 수정</button>
                <button className="btn btn-danger" onClick={handleDelete}>🗑️ 삭제</button>
            </div>
        </div>
    );
};

export default BlogDetail;
