import {Route, Routes} from "react-router-dom";
import './App.css'
import AllBlogs from "./pages/AllBlogs.tsx";
import NewBlog from "./pages/NewBlog.tsx";
import BlogDetail from "./pages/BlogDetail.tsx";
import EditBlog from "./pages/EditBlog.tsx";

function App() {

  return (
    <Routes>
      <Route path="/" element={<AllBlogs/>} />
      <Route path="/board/new" element={<NewBlog/>}/>
      <Route path="/board/:id" element={<BlogDetail/>}/>
      <Route path="/board/edit/:id" element={<EditBlog />} />
    </Routes>
  );
}

export default App;
