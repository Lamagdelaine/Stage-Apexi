<form method="post" action="registration">
    <fieldset>
        <legend>Inscription</legend>
        <p>Remplissez ce formulaire pour vous inscrire.</p>

		<ul>
			<li>
		        <label for="email">Adresse email <span class="requis">*</span></label>
		        <input type="email" id="email" name="email" value="${ member.email }" size="20" maxlength="60" />
		        <span class="error">${form.errors['email']}</span>
			</li>
			<li>
		        <label for="password">Mot de passe <span class="requis">*</span></label>
		        <input type="password" id="password" name="password" value="" size="20" maxlength="20" />
		        <span class="error">${form.errors['password']}</span>
			</li>
			<li>
		        <label for="password">Confirmez <span class="requis">*</span></label>
		        <input type="password" id="password2" name="password2" value="" size="20" maxlength="20" />
		        <span class="error">${form.errors['password2']}</span>
			</li>
			<li>
		        <button type="submit" class="sansLabel" >Valider</button>
			</li>
		</ul>
        <p class="${empty form.errors ? 'succes' : 'error'}">${form.result}</p>
    </fieldset>
</form>
