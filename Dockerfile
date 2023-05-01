ARG SPPATH

FROM node:16

ARG SPPATH
ENV SPPATH=${SPPATH}

# Create app directory
WORKDIR /usr/src/app

# Install app dependencies
# A wildcard is used to ensure both package.json AND package-lock.json are copied
# where available (npm@5+)
COPY package*.json ./

RUN npm install
# If you are building your code for production
# RUN npm ci --only=production

# Bundle app source
COPY ./${SPPATH} ./${SPPATH}

EXPOSE 8080
CMD [ "sh", "-c", "node $SPPATH/src/index.js" ]