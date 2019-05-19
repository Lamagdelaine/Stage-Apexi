<form method="post" action="connexion">
    <fieldset>
        <legend>Connexion</legend>
        <p>Vous pouvez vous connecter via ce formulaire.</p>

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
		        <button type="submit" class="sansLabel" >Connexion</button>
			</li>
		</ul>
        <p class="${empty form.errors ? 'succes' : 'error'}">${form.result}</p>
    </fieldset>
</form>
