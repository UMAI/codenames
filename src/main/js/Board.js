import React, {Component} from 'react';
import Word from './Word';

class Board extends Component {
	constructor(props) {
		super(props);
	}

	renderWord(i) {
		return (
			<Word word={this.props.words[i]} />
		);
	}

	render() {
		return (
			<div className="board">
				<div className="board-row">
					{this.renderWord(0)}
					{this.renderWord(1)}
					{this.renderWord(2)}
					{this.renderWord(3)}
					{this.renderWord(4)}
				</div>
				<div className="board-row">
					{this.renderWord(5)}
					{this.renderWord(6)}
					{this.renderWord(7)}
					{this.renderWord(8)}
					{this.renderWord(9)}
				</div>
				<div className="board-row">
					{this.renderWord(10)}
					{this.renderWord(11)}
					{this.renderWord(12)}
					{this.renderWord(13)}
					{this.renderWord(14)}
				</div>
				<div className="board-row">
					{this.renderWord(15)}
					{this.renderWord(16)}
					{this.renderWord(17)}
					{this.renderWord(18)}
					{this.renderWord(19)}
				</div>
				<div className="board-row">
					{this.renderWord(20)}
					{this.renderWord(21)}
					{this.renderWord(22)}
					{this.renderWord(23)}
					{this.renderWord(24)}
				</div>
			</div>
		);
	}
}

export default Board;