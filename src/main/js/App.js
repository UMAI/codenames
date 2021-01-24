//const client = require('./client');
import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import Board from './Board';

class App extends Component {
	constructor(props) {
		super(props);
		this.state = {words: []};
	}

	componentDidMount() {
		fetch('/api/words').then(response => response.json()).then(data => this.setState(data));
		//client({method: 'GET', path: '/api/words'}).done(response => this.setState({words: response.entity._embedded.words}));
	}

	render() {
		return (
			<Board words={this.state.words} />
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)