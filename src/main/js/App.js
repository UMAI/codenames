import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import {Switch, Route, BrowserRouter as Router} from 'react-router-dom';
import Menu from './Menu';
import Lobby from './Lobby';

class App extends Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<div className="app">
				<Router>
					<Switch>
						<Route exact path="/" component={Menu} />
						<Route path="/lobby" component={Lobby} />
					</Switch>
				</Router>
			</div>
		);
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)