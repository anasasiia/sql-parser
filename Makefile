.DEFAULT-GOAL := build-run

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	app/build/install/app/bin/app

run:
	./gradlew run

lint:
	./gradlew checkstyleMain

build-run: build run

.PHONY: build