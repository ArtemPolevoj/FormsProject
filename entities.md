	Заказчик:
		id - not null 
		name - not null 
		email - на всякий случай
		телефон - на всякий случай
	Исполнитель:
		id - not null  
		name - not null 
		email - на всякий случай
		телефон - на всякий случай
	Машина:
		id - not null 
		производитель - not null 
		модель - not null 
		серийный номер - not null 
		хозяйственный номер - not null
        наработка - not null
	Неисправность:
		id - not null 
		дата обнаружения - not null 
		описание - not null 
		зап.часть - not null 
		работы по устранению - not null 
        фотографии
		дата устранения
		флаг устранения - булево.  - not null
	Вопрос:
		номер - not null 
		текст вопроса - not null 
		ответ - not null 
		неиспрвность
    Пожелания:
		текст
		фото
	Отчет:
		id - not null 
		дата - not null 
		исполнитель - not null
        фото общего вида
        Map<String, String> - not null (предварительные действия)
		заказчик - not null 
		машина - not null 
		список вопросов - not null
        пожелания