import {useNavigate, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import axios from "axios";


const EditBlog = () =>{
    const {id} = useParams();
    const navigate = useNavigate();
    const [board,setBoard] = useState({title:"",content:"",email:""});

    useEffect(() => {
        axios.get(`http://localhost:8091/api/boards/${id}`)
            .then(response => {
                setBoard(response.data);
            })
            .catch(err => {
                console.error("불러오기 실패",err);
            });
    }, [id]);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) =>{
        const {name, value} = e.target;
        setBoard(perv => ({...perv, [name]: value}));
    }

    const handleSubmit = (e: React.FormEvent) =>{
        e.preventDefault();
        axios.put(`http://localhost:8091/api/boards/${id}` , board)
            .then(() =>{
                alert("수정완료!");
                navigate(`/`);
            })
            .catch(err => {
                console.error("수정실패" ,err);
            });
    };

    return (
        <div className="container mt-5">
            <h2>🛠 게시글 수정</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label className="form-label">제목</label>
                    <input className="form-control" name="title" value={board.title} onChange={handleChange} required />
                </div>
                <div className="mb-3">
                    <label className="form-label">작성자</label>
                    <input className="form-control-plaintext fw-bold" name="name" value={board.email} onChange={handleChange} required />
                </div>
                <div className="mb-3">
                    <label className="form-label">내용</label>
                    <textarea className="form-control" name="content" value={board.content} onChange={handleChange} required />
                </div>
                <button type="submit" className="btn btn-primary">수정</button>
            </form>
        </div>

    );
};

export default EditBlog;