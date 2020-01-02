#!/bin/bash

# Ensure clean state (not reqired on GitHub CI, but usefull to replciate GitHub CI localy).
brew cask uninstall google-chrome -f
sudo rm -rf /Library/Google/*
rm -rf ~/Library/Google/*
rm -rf ~/Library/Caches/Homebrew/downloads/*googlechrome.dmg
rm -rf ~/.m2/repository/webdriver

# Install Google Chrome
brew update
brew cask install google-chrome

# Allow Google Chrome to start without prompt user for permissions
xattr -d -r com.apple.quarantine /Applications/Google\ Chrome.app
