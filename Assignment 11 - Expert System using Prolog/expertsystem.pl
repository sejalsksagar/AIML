% Common Diseases Diagnosis Expert System 

main:-
	write('**** Common Diseases Diagnosis Expert System ****'),nl,
	disease(Disease),
	write('Take care!'),nl,
	undo;
	write('System unable to determine the disease.'),nl,
	undo.

disease(anemia):-
	symptom(fatigue),
	symptom(dizziness),
	symptom(weakness),
	symptom(shortness_of_breath),
	symptom(pale_skin),
	write('You are diagnoised with Anemia.'), nl,!.
	
disease(anxiety):-
	symptom(nervousnes),
	symptom(restlessness),
	symptom(panic),
	symptom(hyperventilation),
	symptom(sweating),
	write('You may have Anxiety.'), nl,!.
	
disease(depression):-
	symptom(fatigue),
	symptom(saddness),
	symptom(no_energy),
	symptom(no_interest),
	symptom(irritability),
	write('You may have Depression.'), nl,!.
	
disease(insomnia):-
	symptom(fatigue),
	symptom(sleep_issues),
	symptom(stress),
	symptom(headache),
	symptom(dificulty_falling_asleep),
	symptom(dificulty_waking_up),
	symptom(no_energy_after_waking_up),
	write('You have Insomnia.'), nl,!.
	
disease(anorexia):-
	symptom(fatigue),
	symptom(dizziness),
	symptom(weakness),
	symptom(shortness_of_breath),
	symptom(thin_appearance),
	symptom(extreme_fear_of_gaining_weight),
	symptom(weight_loss),
	write('You are diagnoised with Anorexia.'), nl,!.
	
disease(heart_attack):-
	symptom(chest_pain),
	symptom(dizziness),
	symptom(shortness_of_breath),
	symptom(sweating),
	write('You maybe having a heart attack.'), nl,!.
	
disease(common_cold):-
	symptom(runny_nose),
	symptom(sore_throat),
	symptom(cough),
	symptom(congestion),
	symptom(headache),
	write('You have a Cold.'), nl,!.
	
disease(influenza):-
	symptom(headache),
	symptom(cough),
	symptom(chills),
	symptom(fever),
	symptom(body_pain),
	write('You are diagnoised with Influenza.'), nl,!.
	
disease(tuberculosis):-
	symptom(persistent_coughs),
	symptom(cough_with_blood),
	symptom(chest_pain),
	symptom(night_sweats),
	symptom(weight_loss),
	write('You are diagnoised with Tuberculosis.'), nl,!.
	
disease(malaria):-
	symptom(fever),
	symptom(chills),
	symptom(muscle_pain),
	symptom(nausea),
	symptom(headache),
	write('You may have Malaria.'), nl,!.
	
disease(typhoid):-
	symptom(fever),
	symptom(adominal_pain),
	symptom(headache),
	symptom(dry_cough),
	symptom(weight_loss),
	write('You may have Typhoid.'), nl,!.
	
disease(jaundice):-
	symptom(yellow_eyes),
	symptom(yellow_urine),
	symptom(fever),
	symptom(weight_loss),
	symptom(vomitting),
	write('You are diagnoised with Jaundice.'), nl,!.

ask(Question):-
	write('Do you experience '),write(Question), write('?: '),
	read(Response),nl,
	((Response==y; Response==yes)
	->assert(yes(Question));assert(no(Question)), fail).
	:- dynamic yes/1,no/1.

symptom(Symptom):-
	yes(Symptom)->true;
	no(Symptom)->fail;
	ask(Symptom).

undo:-
	retract(yes()), fail.
undo:-
	retract(no()), fail.
undo.

:-main.