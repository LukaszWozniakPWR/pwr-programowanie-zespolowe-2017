#!/usr/bin/env bash

rm -f src/assets.js
echo "const assets = {};" > src/assets.js


# cards
while read -r line; do
    module="${line%.*}"
    module="${module// /_}"
    echo "assets[\"../assets/cards/${line}\"] = require(\"../assets/cards/${line}\");" >> src/assets.js
done < <(ls assets/cards)


echo "export default assets;" >> src/assets.js
