# 1: First Script

**Goal:** Go from zero to running bash scripts with security awareness.

## 1.1 What is Bash?

```bash
echo $SHELL
bash --version
```

- **Terminal:** The window you type in
- **Shell:** A program that interprets your commands
- **Bash:** Bourne Again Shell - The standard Linux shell since 1995-96

> "Bash isn’t just for running commands - it’s a full programming language."

Bash has variables, conditionals, loops, and functions. It’s not just a command runner - it's a scripting language that’s on every Linux server.

**Why bash over zsh/fish?** Bash is the standard. When you SSH into a server, it's running bash. Write portable scripts that work everywhere.

**Why write scripts?** Instead of typing commands one by one, write them once and run whenever needed. Automation, repeatability, documentation.

---

## 1.2 Creating Your First Script

```bash
vim hello
```

```bash
#!/bin/bash

echo "Hello, DevOps!"
```

### The Shebang - Security Critical

The first line ```#!/bin/bash``` tells the system which interpreter to use.

Use ```#!/bin/bash```, NOT ```#!/usr/bin/env bash```

Why? ```#!/usr/bin/env bash``` searches your PATH for bash. An attacker can put a malicious “bash” earlier in your PATH. With ```#!/bin/bash``` you know exactly what runs.

### No File Extensions

Name scripts without extensions (```hello```, not ```hello.sh```). The shebang tells the system what interpreter to use.

The ```.sh``` extension is a convention for humans, not a requirement. Real Unix tools don’t have extensions - ```ls```, ```grep```, ```vim```. Your scripts shouldn’t either.

---

## 1.3 Running Scripts

Method 1: Explicit interpreter

```bash
bash hello
```

Method 2: Make executable

```bash
chmod +x hello
./hello
```

Why ```./```? The current directory isn’t in PATH (security feature). Never add ```.``` to your PATH.

> "Adding ```.``` to your PATH is how you get hacked. Someone drops a malicious ```‘ls’``` in a directory you visit, game over."

### Debug Mode

When scripts misbehave, run with ```-x``` to see each command as it executes:

```bash
bash -x hello
```

This shows variable expansion and command execution - invaluable for debugging.

Before asking for help, run your script with ```bash -x``` first. You’ll often spot the problem immediately.

---

## 1.4 Shellcheck - Mandatory

Shellcheck finds bugs and security issues in shell scripts.

```bash
# Install
sudo apt install shellcheck    # Debian/Ubuntu
sudo dnf install ShellCheck    # Fedora

# Use
shellcheck hello
```

Run shellcheck on EVERY script before committing or deploying. Not optional. Not "nice to have." Mandatory.

---

## 1.5 Practical Example

```bash
#!/bin/bash

# Description: Display system information

echo "=== System Information ==="
echo "Hostname: $(hostname)"
echo "Date: $(date)"
echo "Kernel: $(uname -r)"
```

```bash
shellcheck sysinfo
chmod +x sysinfo
./sysinfo
```

Always add a description comment. Six months from now, you won’t remember what the script does.
