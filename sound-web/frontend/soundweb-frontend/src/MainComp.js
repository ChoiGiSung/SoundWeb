import React from "react";
import axios from "axios";

class MainComponent extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            message: ""
        }
    }

    componentDidMount() {
        this.getApi();
    }

    getApi = () => {
        axios.get("http://localhost:8080/api/hello")
            .then(res => {
                console.log(res);
                this.setState({
                    message: res.data.message
                })
            })
            .catch(res => console.log(res))
    }

    render() {
        return(
            <div>
                Main ÆäÀÌÁö
            </div>
        )
    }
}

export default MainComponent