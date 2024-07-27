	Заказчик:
		id - not null 
		name - not null 
		email
		телефон
	Исполнитель:
		id - not null  
		name - not null 
		email
		телефон
	Машина:
		id - not null 
		производитель - not null 
		модель - not null 
		серийный номер - not null 
		хозяйственный номер - not null
	Неисправность:
		id - not null 
		дата обнаружения - not null 
		описание - not null 
		зап.часть - not null 
		работы по устранению - not null 
		дата устранения
		флаг устранения - булево.  - not null 
	Отчет:
		id - not null 
		дата - not null 
		исполнитель - not null 
		заказчик - not null 
		машина - not null 
		список неисправностей - not null(только присутствующие)