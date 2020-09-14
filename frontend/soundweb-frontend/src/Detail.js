import React from "react";



class Detail extends React.Component {
    componentDidMount() {
        const { location,history } = this.props;
        console.log(location.state);
        if (location.state === undefined) {
            history.push("/"); // 무비디테일로 get으로 이동하면 홈으로 이동시키기
        }
    }

    render() {
        const { location } = this.props;
        if (location.state) {
            return <span>{location.state.title}</span>;
        } else {
            return null;
        }
        
    }
}







//function Detail({ location}) {
//    console.log(location);
//    return <span>dddddddddd</span>
//}
export default Detail;