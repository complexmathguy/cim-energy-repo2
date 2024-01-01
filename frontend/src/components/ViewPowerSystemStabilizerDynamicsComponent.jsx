import React, { Component } from 'react'
import PowerSystemStabilizerDynamicsService from '../services/PowerSystemStabilizerDynamicsService'

class ViewPowerSystemStabilizerDynamicsComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            powerSystemStabilizerDynamics: {}
        }
    }

    componentDidMount(){
        PowerSystemStabilizerDynamicsService.getPowerSystemStabilizerDynamicsById(this.state.id).then( res => {
            this.setState({powerSystemStabilizerDynamics: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View PowerSystemStabilizerDynamics Details</h3>
                    <div className = "card-body">
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewPowerSystemStabilizerDynamicsComponent
