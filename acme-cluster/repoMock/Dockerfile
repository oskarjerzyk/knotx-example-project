FROM acme-core:latest

## Copy ACME specific configs
ADD conf ${KNOTX_HOME}/conf
ADD sample ${KNOTX_HOME}/sample

EXPOSE 3001

CMD ["run-knotx"]
