import React, { Component } from 'react'
import UnderexcitationLimiterDynamicsService from '../services/UnderexcitationLimiterDynamicsService'

class ViewUnderexcitationLimiterDynamicsComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            underexcitationLimiterDynamics: {}
        }
    }

    componentDidMount(){
        UnderexcitationLimiterDynamicsService.getUnderexcitationLimiterDynamicsById(this.state.id).then( res => {
            this.setState({underexcitationLimiterDynamics: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View UnderexcitationLimiterDynamics Details</h3>
                    <div className = "card-body">
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewUnderexcitationLimiterDynamicsComponent
