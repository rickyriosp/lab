[```git-subtree```](https://github.com/git/git/blob/795c338de725e13bd361214c6b768019fc45a2c1/contrib/subtree/git-subtree.adoc) is a script designed for exactly this use case of merging multiple repositories into one while preserving history (and/or splitting history of subtrees, though that is seems to be irrelevant to this question). It is distributed as part of the git tree since release [1.7.11](https://github.com/git/git/commit/634392b26275fe5436c0ea131bc89b46476aa4ae).

To merge a repository ```<repo>``` at revision ```<rev>``` as subdirectory ```<prefix>```, use git subtree add as follows:

```bash
git subtree add -P <prefix> <repo> <rev>
```

git-subtree implements the [subtree merge strategy](https://git-scm.com/book/en/v1/Git-Tools-Subtree-Merging) in a more user friendly manner.

The **downside** is that in the merged history the files are unprefixed (not in a subdirectory). Say you merge repository ```a``` into ```b```. As a result ```git log a/f1``` will show you all the changes (if any) except those in the merged history. You can do:

```
git log --follow -- f1
```

but that won't show the changes other then in the merged history.

In other words, if you don't change a's files in repository b, then you need to specify --follow and an unprefixed path. If you change them in both repositories, then you have 2 commands, none of which shows all the changes.

More on it [here](https://gist.github.com/x-yuri/8ad01701db51ec2891ca431b78c58c72).
