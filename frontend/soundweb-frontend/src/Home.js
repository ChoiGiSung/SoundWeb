//import React from "react";
//import PropTypes from "prop-types";
//import axios from "axios";
//import Movie from "../components/Movies";
//import "./Home.css";
//
//
//class Home extends React.Component {
//
//    state = {
//        isLoading: true,
//        movies: []
//    };
//
//    getMovies = async () => {//async ,await 비동기에 관한 것들
//        const { data: { data: { movies } } } = await axios.get("https://yts.mx/api/v2/list_movies.json?sort_by=rating");
//        //처음 movies를 변수를 통해 받아서 json을 읽어보면 위와 같은 경로에 데이터가 들어가 있다
//        this.setState({ movies: movies, isLoading: false });
//    }
//
//    componentDidMount() { //컴포넌트가 처음 마운트 될때 호출
//        this.getMovies(); //axios.get은 느리므로 async을 componentDidMount 앞에 쓰던가 함수로 빼서 나중에 호출 하던가
//    }
//
//    render() {
//        const { isLoading, movies } = this.state;
//        return (
//            <section className="container">
//            {isLoading ? (
//                <div className="loader">
//                    <span className="loader_text">Loading...</span>
//                </div>
//            )
//                : (
//                    <div className="movies">
//                        {movies.map(movie => (
//                            <Movie
//                                key={movie.id}
//                                id={movie.id}
//                                year={movie.year}
//                                title={movie.title}
//                                summary={movie.summary}
//                                poster={movie.medium_cover_image}
//                                genres={movie.genres}
//                            />
//                        ))}
//                    </div>
//                )}
//        </section>);
//    }
//}
//
//
//
//
//export default Home;
