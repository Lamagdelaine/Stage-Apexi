<form method="post" action="nouveau-trajet">
    <fieldset>
        <legend>Nouveau trajet</legend>
        <p>Veuillez remplir les champs nécessaires Ã  la création d'un nouveau trajet.</p>

		<ul>
			<li>
		<!-- Ville de départ -->
				<p><label for="departure_city">Ville de départ <span class="requis">*</span></label>
		        <input type="text" id="departure_city" name="departure_city" value="${ city.name }" /></p>
		        <p><span class="message error">${errors['departure']}</span></p>
			</li>
			<li>
		<!-- Ville d'arrivée -->
				<p><label for="arrival_city">Ville d'arrivée <span class="requis">*</span></label>
		        <input type="text" id="arrival_city" name="arrival_city" value="${ city.name }" min="1" max="24" /></p>
		        <p><span class="message error">${errors['arrival']}</span></p>
			</li>
			<li>
 		<!-- Date et heure de départ --> 
		 		<p><label for="departure_date">Date de départ <span class="requis">*</span></label>
		        <input type="date" id="departure_date" name="departure_date" /></p>
		        <p><span class="message error">${errors['departure_date']}</span></p>
			</li>
			<li>
		 		<p><label for="departure_time">Heure de départ <span class="requis">*</span></label>
		        <input type="time" id="departure_time" name="departure_time" /></p>
		        <p><span class="message error">${errors['departure_time']}</span></p>
			</li>
			<li>
		<!-- Date et heure d'arrivée -->
		 		<p><label for="arrival_date">Date d'arrivée <span class="requis">*</span></label>
		        <input type="date" id="arrival_date" name="arrival_date" /></p>
		        <p><span class="message error">${errors['arrival_date']}</span></p>
			</li>
			<li>
		 		<p><label for="arrival_time">Heure d'arrivée <span class="requis">*</span></label>
		        <input type="time" id="arrival_time" name="arrival_time" /></p>
		        <p><span class="message error">${errors['arrival_time']}</span></p>
			</li>

			<li>	
		<!-- Nombre de passagers maximum -->
		        <p><label for="seat_max">Nombre de places disponibles <span class="requis">*</span></label>
		        <input type="number" id="seat_max" name="seat_max" value="${ travel.seat_max }" min="1" max="4" /></p>
		        <p><span class="message error">${errors['seat_max']}</span></p>
			</li>
			<li>
		<!-- Nombre de bagages au total -->
		        <p><label for="luggage_max">Nombre de bagages au total <span class="requis">*</span></label>
		        <input type="number" id="luggage_max" name="luggage_max" value="${ travel.luggage_max }" min="0" max="10" /></p>
		        <p><span class="message error">${errors['seat_max']}</span></p>
			</li>
			<li>
		        <button type="submit" class="sansLabel" >Valider le trajet</button>
			</li>
		</ul>
        <p class="${empty form.errors ? 'succes' : 'error'}">${form.result}</p>
    </fieldset>
</form>