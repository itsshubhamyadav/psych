package com.shubham.psych.game;

import com.shubham.psych.game.model.EllenAnswer;
import com.shubham.psych.game.model.Round;

public interface EllenStrategy {

	EllenAnswer getAnswer(Round round);
}
