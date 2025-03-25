import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import {getCookie} from "../utils/cookie.ts";

const AllBlogs = () => {
    const [boards, setBoards] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const token = getCookie("jwt");

        if(!token) {
            console.warn("jwt 토큰 존재 x");
            return;
        }

        axios.get("http://localhost:8091/api/boards" , {
            headers: {
                Authorization: `Bearer ${token}`
            },
            withCredentials: true
        })
            .then(response => {
                setBoards(response.data);
            })
            .catch(error => {
                console.log("게시판 불러오기 실패", error);
            });
    }, []);

    const goToDetail = (id: number) => {
        navigate(`/board/${id}`);
    };

    const goToWrite = () =>{
        navigate('/board/new');
    }

    return (
        <div className="container mt-5">
            <h2 className="mb-4">📋 게시판</h2>
            <table className="table table-hover">
                <thead className="table-dark">
                <tr>
                    <th>ID</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>내용</th>
                </tr>
                </thead>
                <tbody>
                {boards.map((board: any) => (
                    <tr key={board.id} style={{ cursor: "pointer" }} onClick={() => goToDetail(board.id)}>
                        <td>{board.id}</td>
                        <td>{board.title}</td>
                        <td>{board.email}</td>
                        <td className="text-truncate" style={{ maxWidth: "300px" }}>{board.content}</td>
                    </tr>
                ))}
                </tbody>
            </table>
            <div className="text-end mt-3">
                <button className="btn btn-success" onClick={goToWrite}>작성하기</button>
            </div>
        </div>
    );
};


export default AllBlogs;
