lis = document.querySelectorAll('ul li');
experiment = document.querySelector('.experiment');
active = document.querySelector('.active');
if (!window.localStorage['data-color'])
	window.localStorage['data-color'] = 'red';
experiment.style.backgroundColor = window.localStorage['data-color'];
active.classList.remove('active');
active = document.querySelector(`[data-color="${window.localStorage['data-color']}"]`);
active.classList.add('active');
lis.forEach((li) => {
	li.addEventListener('click', (e) => {
		active.classList.remove('active');
		li.classList.add('active');
		active = li;
		window.localStorage['data-color'] = li.dataset.color;
		experiment.style.backgroundColor = window.localStorage['data-color'];
	})
});